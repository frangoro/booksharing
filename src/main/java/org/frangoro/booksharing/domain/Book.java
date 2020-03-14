package org.frangoro.booksharing.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String title;
	@NotBlank
	private String author;
	private enum Status {
		AVAILABLE, BORROWED
	}
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private User owner;
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "reader")
	private User reader;

	//TODO Make a builder
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getReader() {
		return reader;
	}
	public void setReader(User reader) {
		this.reader = reader;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", owner=" + owner + ", user=" + reader
				+ "]";
	}

}
