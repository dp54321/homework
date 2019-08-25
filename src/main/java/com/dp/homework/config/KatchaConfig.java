package com.dp.homework.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author dp
 * @create 2019-08-25 2:19
 * 配置验证码的样式
 */
@Configuration
public class KatchaConfig {


    @Bean
    public DefaultKaptcha producer(){
        Properties propertis = new Properties();
        propertis.put("kaptcha.border", "no");
        propertis.put("kaptcha.image.height", "38");
        propertis.put("kaptcha.image.width", "150");
        propertis.put("kaptcha.textproducer.font.color", "black");
        propertis.put("kaptcha.textproducer.font.size", "32");
        Config config = new Config(propertis);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;

    }


}
