package com.fayardev.regms.userservice.config;

import com.fayardev.regms.userservice.util.BooleanToStringConverter;
import com.fayardev.regms.userservice.util.StringToBooleanConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.odm.core.impl.DefaultObjectDirectoryMapper;
import org.springframework.ldap.odm.typeconversion.impl.ConversionServiceConverterManager;

@Configuration
public class LdapConfig {

    @Bean
    public DefaultConversionService myObjectDirectoryMapper(LdapTemplate ldapTemplate) {
        DefaultObjectDirectoryMapper objectDirectoryMapper = (DefaultObjectDirectoryMapper) ldapTemplate.getObjectDirectoryMapper();
        DefaultConversionService conversionService = new DefaultConversionService();

        conversionService.addConverter(new BooleanToStringConverter());
        conversionService.addConverter(new StringToBooleanConverter());

        ConversionServiceConverterManager converterManager = new ConversionServiceConverterManager(conversionService);
        objectDirectoryMapper.setConverterManager(converterManager);

        return conversionService;
    }
}
