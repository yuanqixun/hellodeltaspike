import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by yqx on 3/28/15.
 */
@Entity
@Table(name="TB_DEMO_PERSON")
public class Person {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "D_UUID", nullable = false, length = 64)
  protected String uuid;

  /**
   * 限制用户
   */
  @Column(name = "D_NAME", length = 64)
  protected String name;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
