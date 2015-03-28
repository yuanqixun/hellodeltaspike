import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by yqx on 3/28/15.
 */
@ApplicationScoped
public class EntityManagerProducer {

  @PersistenceUnit(unitName = "hellodeltaspike")
  EntityManagerFactory emf;

  @PersistenceUnit(unitName = "hellodeltaspike2")
  EntityManagerFactory mysqlemf;

  @Produces
  @ConversationScoped
  EntityManager createEntityManager(){
    return this.emf.createEntityManager();
  }

  @Produces
  @MySqlEm
  @ConversationScoped
  EntityManager createMysqlEntityManager(){
    return this.mysqlemf.createEntityManager();
  }

}
