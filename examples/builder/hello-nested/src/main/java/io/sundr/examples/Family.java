/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.examples;

import java.util.List;
import java.util.stream.Collectors;
import io.sundr.builder.annotations.Buildable;
  
@Buildable
public class Family {

  Person father;
  Person mother;
  List<Person> children;

  public Family(Person father, Person mother, List<Person> children) {
    this.father = father;
    this.mother = mother;
    this.children = children;
  }

  public Person getFather() {
    return this.father;
  }

  public Person getMother() {
    return this.mother;
  }

  public List<Person> getChildren() {
    return this.children;
  }

  public String toString() {
    return "[" + father + " & " + mother + "{" + children.stream().map(Person::toString).collect(Collectors.joining(",")) + "}]";
  }
}
