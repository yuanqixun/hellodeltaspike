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

  Person person;

  List<Person> personList;

  @Inject
  PersonDao personDao;

  @PostConstruct
  void afterCreate(){
    person = new Person();
    //personList = personDao.queryPersonList();
  }

  @Transactional
  public void btnDoSave(ActionEvent event){
    try {
      person = personDao.savePerson(person);
      String msg = "Saved:"+person.getName();
      FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
      person = new Person();
      personList = personDao.queryPersonList();
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
