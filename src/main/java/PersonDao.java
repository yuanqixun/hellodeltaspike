import org.apache.deltaspike.core.util.StringUtils;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqx on 3/28/15.
 */
@Dependent
public class PersonDao implements Serializable{

  @Inject
  EntityManager em;

  @Inject
  @MySqlEm
  EntityManager mysqlEm;

  public EntityManager getEm() {
    return em;
  }


  public EntityManager getMysqlEm() {
    return mysqlEm;
  }


  public List<Person> queryPersonList() {
    String jql = "select o from Person o";
    List<Person> result = mysqlEm.createQuery(jql, Person.class).getResultList();
    if (result == null)
      return new ArrayList<Person>();
    return result;
  }

  public Person savePerson(Person person) {
    if(person == null)
      return null;
    if (StringUtils.isEmpty(person.getUuid())) {
      mysqlEm.persist(person);
    } else {
      person = mysqlEm.merge(person);
    }
    mysqlEm.flush();
    return person;
  }

}
