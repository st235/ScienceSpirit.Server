package com.github.sasd97.core;

import com.github.sasd97.constants.AppConstants;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Alexadner Dadukin on 11/20/2016.
 */

@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
@ComponentScan(AppConstants.DEFAULT_PACKAGE)
@SpringBootApplication
public class ServerCore {}
