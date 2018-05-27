package org.hbs.disys;

import org.apache.catalina.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = { "org.hbs" })
@EntityScan(basePackages = { "org.hbs" })
@PropertySources({ @PropertySource("classpath:application.properties"), @PropertySource("classpath:validation.properties") })
public class DISYSMainApplication extends SpringBootServletInitializer
{
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(DISYSMainApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(DISYSMainApplication.class);
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer()
	{
		TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context)
			{
				// final int cacheSize = 40 * 1024;
				// StandardRoot standardRoot = new StandardRoot(context);
				// standardRoot.setCacheMaxSize(cacheSize);
				// context.setResources(standardRoot);

				/*
				 * logger.info(String.format("New cache size (KB): %d", context
				 * .getResources().getCacheMaxSize()));
				 */
			}
		};
		return tomcatFactory;
	}
}
