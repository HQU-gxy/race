package com.weihua.race.wrapper;

import com.weihua.race.domain.bean.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * <p>
 * 用户角色参数包装器
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2023/3/2 11:33
 */
@Slf4j
public class AuthParamWrapper extends HttpServletRequestWrapper {

    public static final String COMPANY_ID = "companyId";
    public static final String MAINTAIN_COMPANY_ID = "maintainCompanyId";
    public static final String REPAIR_COMPANY_ID = "repairCompanyId";

    public static final String GROUP_ID = "groupId";
    public static final String MAINTAIN_GROUP_ID = "maintainGroupId";
    public static final String REPAIR_GROUP_ID = "repairGroupId";
    public static final String UNIT_ID = "unitId";
    public static final String JWT_TOKEN = "token";

    @Setter
    private HashMap<String,String> convertMap = new HashMap<>();
    /**
     * JWTToken
     */
    private String token;
    /**
     * 用户身份信息
     */
    @Getter
    private UserInfo userInfo;
    /**
     * 是否需要包装参数。 <br>
     * 查询公司、单位、设备等需要分角色查看不同范围数据时需要置为真。
     */
    @Setter
    private boolean needWrapper = false;

    public AuthParamWrapper(HttpServletRequest request) {
        super(request);
        token = super.getHeader(JWT_TOKEN);
        userInfo = new UserInfo(token);
    }

    /**
     * 获取请求中的参数名列表,调用过程见 {@link #getParameterValues(String)}
     * @return 参数名称列表
     */
    @Override
    public Enumeration<String> getParameterNames() {
        if (needWrapper) {
            return new Vector<>(getWrapParameterMap().keySet()).elements();
        }
        return super.getParameterNames();
    }

    /**
     *  获取参数值数组 <br>
     *  在springboot的参数解析器将查询参数转换为查询实体时,
     *  将会调用 {@link WebUtils#getParametersStartingWith(ServletRequest, String)}方法, <br>
     *  该方法中会先调用 {@link #getParameterNames()}获取请求中的参数键,
     *  然后调用本方法获取参数值. <br>
     *  故若要修改请求中的参数,需要对本方法进行包装.
     * @param name 参数键
     * @return 参数值数组
     */
    @Override
    public String[] getParameterValues(String name) {
        if (needWrapper) {
            return getWrapParameterValues(name);
        }
        return super.getParameterValues(name);
    }

    private String[] getWrapParameterValues(String name) {
        // 角色为admin,无需改动任何查询参数值
        if (userInfo.isAdmin()){
            return super.getParameterValues(name);
        }
        // 角色为公司级别级别
        // 查询参数中的companyId只能是自己所属的公司id
        if (convertMap.getOrDefault(COMPANY_ID,COMPANY_ID).equals(name)) {
            return new String[]{userInfo.getCompanyId()};
        }
        // 角色为工人级别
        // 查询参数中的groupId只能是自己所属的班组id
        if (convertMap.getOrDefault(GROUP_ID,GROUP_ID).equals(name)) {
            return new String[]{userInfo.getGroupId()};
        }

        if (UNIT_ID.equals(name)) {
            // 角色是单位或管理员级别的将查询参数中的unitId置为自己所属的unitId
            if (userInfo.isUnit() || userInfo.isWorker()){
                return new String[]{userInfo.getUnitId()};
            }else {
                // 角色是公司级别的,
                //若查询参数中有unitId,就返回原有的unitId,无需改动
                return getWrapParameterMap().get(UNIT_ID);
            }
        }
        return super.getParameterValues(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        if (needWrapper) {
            return getWrapParameterMap();
        }
        return super.getParameterMap();
    }


    private Map<String, String[]> getWrapParameterMap() {
        HashMap<String, String[]> paramsMap = new HashMap<>(super.getParameterMap());
        // 公司级账号只能查询本公司的数据，将公司id添加到请求的查询参数中
        if (userInfo.isCompany()) {
            paramsMap.put(convertMap.getOrDefault(COMPANY_ID, COMPANY_ID), new String[]{userInfo.getCompanyId()});
        }
        // 工人级账号只能查询本班组的数据，将班组id添加到请求的查询参数中
        if (userInfo.isWorker()) {
            paramsMap.put(convertMap.getOrDefault(GROUP_ID, GROUP_ID), new String[]{userInfo.getGroupId()});
        }
        // 单位级和实验室管理员级账号只能查询本单位的数据，将单位id添加到请求的查询参数中
        if (userInfo.isUnit() || userInfo.isWorker()) {
            paramsMap.put(UNIT_ID, new String[]{userInfo.getUnitId()});
        }
        return Collections.unmodifiableMap(paramsMap);
    }

    /**
     * 将公司id参数名进行变更.
     * <pre>
     *     例如在查询电梯时，电梯的公司id属性名并不是companyId
     *
     *     例如若账号角色为维保公司，就要进行参数转换:
     *     compnayId -> maintainCompanyId
     *     保证能够进行正确的查询.
     * </pre>
     */
    public void convertCompany(){
        if (userInfo.isRepairCompany() || userInfo.isRepairWorker()){
            convertMap.put(COMPANY_ID,REPAIR_COMPANY_ID);
        }
        if (userInfo.isMaintainCompany() || userInfo.isMaintainWorker()){
            convertMap.put(COMPANY_ID,MAINTAIN_COMPANY_ID);
        }
    }

    public void convertGroup(){
        if (userInfo.isRepairWorker()){
            convertMap.put(GROUP_ID,REPAIR_GROUP_ID);
        }
        if (userInfo.isMaintainWorker()){
            convertMap.put(GROUP_ID,MAINTAIN_GROUP_ID);
        }
    }

}
