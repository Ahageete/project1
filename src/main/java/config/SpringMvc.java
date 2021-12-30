package config;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
@EnableWebMvc
@Configuration
@ComponentScan("project.*.ctrl")
public class SpringMvc implements WebMvcConfigurer
{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		WebMvcConfigurer.super.extendMessageConverters(converters);
	}
	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		CorsRegistration corsRegistration=registry.addMapping("/**").allowedOriginPatterns("*");
		corsRegistration.allowedMethods("OPTIONS","GET","POST","DELETE","PUT").allowedHeaders("*").allowCredentials(true);
		corsRegistration.exposedHeaders("access-control-allow-headers","access-control-allow-methods","access-control-allow-origin","access-control-max-age","X-Frame-Options");
		WebMvcConfigurer.super.addCorsMappings(registry);
	}
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
	{
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		WebMvcConfigurer.super.configureContentNegotiation(configurer);
	}
	@Bean
	public ContentNegotiatingViewResolver viewResolver()
	{
		ContentNegotiatingViewResolver viewResolver=new ContentNegotiatingViewResolver();
		viewResolver.setDefaultViews(Lists.newArrayList(new MappingJackson2JsonView()));
		return viewResolver;
	}
	@Bean
	public ObjectMapper objectMapper()
	{
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		return objectMapper;
	}
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter()
	{
		List<MediaType> supportedMediaTypes=Lists.newArrayList(MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED);
		RequestMappingHandlerAdapter handler=new RequestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> list2=Lists.newArrayList();
		MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(supportedMediaTypes);
		converter.setObjectMapper(this.objectMapper());
		list2.add(converter);
		StringHttpMessageConverter stringHttpMessageConverter=new StringHttpMessageConverter();
		stringHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		list2.add(stringHttpMessageConverter);
		handler.setMessageConverters(list2);
		handler.setIgnoreDefaultModelOnRedirect(true);
		return handler;
	}
	@Bean
	public CommonsMultipartResolver multipartResolver()
	{
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		try
		{
			if(System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			{
				multipartResolver.setUploadTempDir(new FileUrlResource("D:/tmp"));
			}
			else
			{
				multipartResolver.setUploadTempDir(new FileUrlResource("/tmp"));
			}
			multipartResolver.setDefaultEncoding("UTF-8");
			multipartResolver.setMaxInMemorySize(1024*1024*335);
			multipartResolver.setMaxUploadSize(1024*1024*325);
			multipartResolver.setMaxUploadSizePerFile(1024*1024*75);
			multipartResolver.setPreserveFilename(true);
			multipartResolver.setResolveLazily(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return multipartResolver;
	}
}
