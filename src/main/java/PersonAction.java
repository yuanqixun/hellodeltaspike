import org.apache.deltaspike.core.util.StringUtils;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqx on 3/28/15.
 */
@ConversationScoped
@Named
public class PersonAction implements Serializable{

  @Inject
  EntityManager em;

  @Inject
  @MySqlEm
  EntityManager mysqlEm;

  Person person;

  List<Person> personList;

  @PostConstruct
  void afterCreate(){
    person = new Person();
    personList = queryPersonList();
  }

  private List<Person> queryPersonList() {
    String jql = "select o from Person o ";
    List<Person> result = em.createQuery(jql,Person.class).getResultList();
    if(result == null)
      return new ArrayList<Person>();
    return result;
  }


  @Transactional
  public void btnDoSave(ActionEvent event){
    try {
      if(StringUtils.isEmpty(person.getUuid())){
        em.persist(person);
      }else{
        em.merge(person);
      }
      em.flush();
      String msg = "Saved:"+person.getName();
      FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,null));
      person = new Person();
      personList = queryPersonList();
    } catch (Exception e) {
      e.printStackTrace();
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
    }
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public List<Person> getPersonList() {
    return personList;
  }

  public void setPersonList(List<Person> personList) {
    this.personList = personList;
  }
}
