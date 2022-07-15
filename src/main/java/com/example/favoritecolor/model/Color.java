package com.example.favoritecolor.model;

import java.util.HashMap;
import java.util.Map;

public enum Color {
  BLAU(1, "blau"),
  GRÜN(2, "grün"),
  VIOLETT(3, "violett"),
  ROT(4, "rot"),
  GELB(5, "gelb"),
  TÜRKIS(6, "türkis"),
  WEISS(7, "weiß");

  private static final Map<Integer, Color> BY_ID = new HashMap<>();
  private static final Map<String, Color> BY_NAME = new HashMap<>();

  static {
    for (Color c : values()) {
      BY_ID.put(c.id, c);
      BY_NAME.put(c.name, c);
    }
  }
  private final int id;
  private final String name;

  private Color(int id, String name){
    this.id = id;
    this.name = name;
  }

  public static Color valueOfId(int id){
    return BY_ID.get(id);
  }

  public static Color valueOfName(String name){
    return BY_NAME.get(name);
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
