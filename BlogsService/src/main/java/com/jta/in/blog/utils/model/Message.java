package com.jta.in.blog.utils.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * the message object returned in response,
 * it contains a unique id, parameters for incorporating into the generic messages and text.
 */
public class Message   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("params")
  @Valid
  private List<Object> params = null;

  @JsonProperty("text")
  private String text = null;

  public Message id(String id) {
    this.id = id;
    return this;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Message params(List<Object> params) {
    this.params = params;
    return this;
  }

  public Message addParamsItem(Object paramsItem) {
    if (this.params == null) {
      this.params = new ArrayList<Object>();
    }
    this.params.add(paramsItem);
    return this;
  }


  public List<Object> getParams() {
    return params;
  }

  public void setParams(List<Object> params) {
    this.params = params;
  }

  public Message text(String text) {
    this.text = text;
    return this;
  }



  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Message message = (Message) o;
    return Objects.equals(this.id, message.id) &&
        Objects.equals(this.params, message.params) &&
        Objects.equals(this.text, message.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, params, text);
  }

}

