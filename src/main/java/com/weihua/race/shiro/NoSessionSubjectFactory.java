package com.weihua.race.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * <p>
 *     让Shiro创建subject时不启用session的工厂类
 * </p>
 * @author liulingyu
 * @date 2022-05-08 15:45
 * @version 1.0
 */
@Slf4j
public class NoSessionSubjectFactory extends DefaultWebSubjectFactory {


    @Override
    public Subject createSubject(SubjectContext context) {
        context.setSessionCreationEnabled(false);
        //log.debug("NoSessionSubjectFactory 创建");
        return super.createSubject(context);
    }
}
