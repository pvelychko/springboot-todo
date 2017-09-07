package com.pvelychko.model;

import com.pvelychko.converter.LocalDateTimeConverter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "todos")
public class Todo implements Serializable {

	public Todo() {
		// Default constructor
	}

	public Todo(String content) {
		this.content = content;
		this.createdAt = LocalDateTime.now();
	};

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotEmpty(message="The task content field can't be empty!")
	private String content;

	@Column
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdAt;

	private boolean complete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public boolean getComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Todo)) return false;
		Todo that = (Todo) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(content, that.content) &&
				Objects.equals(createdAt, that.createdAt) &&
				Objects.equals(complete, that.complete);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, content, createdAt, complete);
	}

	@Override
	public String toString() {
		return com.google.common.base.MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("content", content)
				.add("createdAt", createdAt)
				.add("completed", complete)
				.toString();
	}
}
