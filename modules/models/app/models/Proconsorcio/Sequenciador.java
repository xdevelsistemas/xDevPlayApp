package models.Proconsorcio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by claytonsantosdasilva on 22/07/14.
 */
@Entity
public class Sequenciador {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long id;
}
