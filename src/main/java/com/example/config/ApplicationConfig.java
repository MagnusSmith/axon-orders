package com.example.config;

import com.example.orders.command.Order;
import com.example.orders.command.OrderCommandHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

@Configuration
@ComponentScan(basePackages = {"com.example.config", "com.example.orders.rest", "com.example.orders.command", "com.example.orders.query"})
public class ApplicationConfig {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("/persistence.properties"));
        return ppc;
    }


    @Bean
    public CommandGatewayFactoryBean commandGateway() {
        CommandGatewayFactoryBean factory = new CommandGatewayFactoryBean();
        factory.setCommandBus(commandBus());
        return factory;
    }


    @Bean
    public OrderCommandHandler orderCommandHandler() {
        return new OrderCommandHandler();
    }

    @Bean
    public CommandBus commandBus() {
        return new SimpleCommandBus();
    }

    @Bean
    AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        AnnotationCommandHandlerBeanPostProcessor handler = new AnnotationCommandHandlerBeanPostProcessor();
        handler.setCommandBus(commandBus());
        return handler;
    }

    @Bean
    public EventStore eventStore() {
        return new FileSystemEventStore(new SimpleEventFileResolver(new File("./events")));
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
        AnnotationEventListenerBeanPostProcessor listener = new AnnotationEventListenerBeanPostProcessor();
        listener.setEventBus(eventBus());
        return listener;
    }

    @Bean(name = "orderRepository")
    public Repository<Order> repository() {
        EventSourcingRepository repository = new EventSourcingRepository<Order>(Order.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }


    public static void main(String[] args) {
//        ApplicationContext ctx = SpringApplication.run(Application.class, args);
//
//        System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
    }

}
