package se.niklas.database.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(value = "datasource.retry.disable", havingValue = "true", matchIfMissing = true)
public class CustomDatasource {

  @Order(Ordered.HIGHEST_PRECEDENCE)
  private class RetryableDataSourceBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {
      if (bean instanceof DataSource) {
        bean = new RetryableDataSource((DataSource)bean);
      }
      return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
      return bean;
    }
  }

  @Bean
  public BeanPostProcessor dataSouceWrapper() {
    return new RetryableDataSourceBeanPostProcessor();
  }

  class RetryableDataSource extends AbstractDataSource {

    private DataSource delegate;

    public RetryableDataSource(DataSource delegate) {
      this.delegate = delegate;
    }

    @Override
    @Retryable(maxAttempts = 10,
        backoff = @Backoff(value = 10000))
    public Connection getConnection() throws SQLException {
      return delegate.getConnection();
    }

    @Override
    @Retryable(maxAttempts = 10,
        backoff = @Backoff(value = 10000))
    public Connection getConnection(String username, String password)
        throws SQLException {
      return delegate.getConnection(username, password);
    }

  }
}
