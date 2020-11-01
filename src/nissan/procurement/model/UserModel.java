package nissan.procurement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */

@Entity
@Table(name="user")
@Getter
@Setter
public class UserModel {
 
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="id")
 protected int id;
 
 @Column(name="name")
 protected String name;
 
 @Column(name="email")
 protected String email;
  
 public UserModel() {
 }
 
 public UserModel(String name, String email, String country) {
  super();
  this.name = name;
  this.email = email;
 }

 public UserModel(int id, String name, String email, String country) {
  super();
  this.id = id;
  this.name = name;
  this.email = email;
 }
 
}
