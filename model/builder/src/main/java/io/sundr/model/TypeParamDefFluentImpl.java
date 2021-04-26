package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;

public class TypeParamDefFluentImpl<A extends TypeParamDefFluent<A>> extends AttributeSupportFluentImpl<A>
    implements TypeParamDefFluent<A> {

  private String name;
  private List<ClassRefBuilder> bounds = new ArrayList<ClassRefBuilder>();

  public TypeParamDefFluentImpl() {
  }

  public TypeParamDefFluentImpl(TypeParamDef instance) {
    this.withName(instance.getName());
    this.withBounds(instance.getBounds());
    this.withAttributes(instance.getAttributes());
  }

  public String getName() {
    return this.name;
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

  public Boolean hasName() {
    return this.name != null;
  }

  public A withNewName(StringBuilder arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(int[] arg1, int arg2, int arg3) {
    return (A) withName(new String(arg1, arg2, arg3));
  }

  public A withNewName(char[] arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(StringBuffer arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(byte[] arg1, int arg2) {
    return (A) withName(new String(arg1, arg2));
  }

  public A withNewName(byte[] arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(char[] arg1, int arg2, int arg3) {
    return (A) withName(new String(arg1, arg2, arg3));
  }

  public A withNewName(byte[] arg1, int arg2, int arg3) {
    return (A) withName(new String(arg1, arg2, arg3));
  }

  public A withNewName(byte[] arg1, int arg2, int arg3, int arg4) {
    return (A) withName(new String(arg1, arg2, arg3, arg4));
  }

  public A withNewName(String arg1) {
    return (A) withName(new String(arg1));
  }

  public A addToBounds(int index, ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToBounds(int index, ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    if (index < 0 || index >= _visitables.get("bounds").size()) {
      _visitables.get("bounds").add(builder);
    } else {
      _visitables.get("bounds").set(index, builder);
    }
    if (index < 0 || index >= bounds.size()) {
      bounds.add(builder);
    } else {
      bounds.set(index, builder);
    }
    return (A) this;
  }

  public A addToBounds(ClassRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<ClassRefBuilder>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToBounds(Collection<ClassRef> items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<ClassRefBuilder>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromBounds(ClassRef... items) {
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromBounds(Collection<ClassRef> items) {
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromBounds(Predicate<ClassRefBuilder> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<ClassRefBuilder> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      ClassRefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  /**
   * This method has been deprecated, please use method buildBounds instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<ClassRef> getBounds() {
    return build(bounds);
  }

  public List<ClassRef> buildBounds() {
    return build(bounds);
  }

  public ClassRef buildBound(int index) {
    return this.bounds.get(index).build();
  }

  public ClassRef buildFirstBound() {
    return this.bounds.get(0).build();
  }

  public ClassRef buildLastBound() {
    return this.bounds.get(bounds.size() - 1).build();
  }

  public ClassRef buildMatchingBound(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : bounds) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Boolean hasMatchingBound(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : bounds) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withBounds(List<ClassRef> bounds) {
    if (this.bounds != null) {
      _visitables.get("bounds").removeAll(this.bounds);
    }
    if (bounds != null) {
      this.bounds = new ArrayList<ClassRefBuilder>();
      for (ClassRef item : bounds) {
        this.addToBounds(item);
      }
    } else {
      this.bounds = null;
    }
    return (A) this;
  }

  public A withBounds(ClassRef... bounds) {
    if (this.bounds != null) {
      this.bounds.clear();
    }
    if (bounds != null) {
      for (ClassRef item : bounds) {
        this.addToBounds(item);
      }
    }
    return (A) this;
  }

  public Boolean hasBounds() {
    return bounds != null && !bounds.isEmpty();
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> addNewBound() {
    return new BoundsNestedImpl();
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> addNewBoundLike(ClassRef item) {
    return new BoundsNestedImpl(-1, item);
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> setNewBoundLike(int index, ClassRef item) {
    return new BoundsNestedImpl(index, item);
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editBound(int index) {
    if (bounds.size() <= index)
      throw new RuntimeException("Can't edit bounds. Index exceeds size.");
    return setNewBoundLike(index, buildBound(index));
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editFirstBound() {
    if (bounds.size() == 0)
      throw new RuntimeException("Can't edit first bounds. The list is empty.");
    return setNewBoundLike(0, buildBound(0));
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editLastBound() {
    int index = bounds.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last bounds. The list is empty.");
    return setNewBoundLike(index, buildBound(index));
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editMatchingBound(Predicate<ClassRefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < bounds.size(); i++) {
      if (predicate.test(bounds.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching bounds. No match found.");
    return setNewBoundLike(index, buildBound(index));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TypeParamDefFluentImpl that = (TypeParamDefFluentImpl) o;
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;
    if (bounds != null ? !bounds.equals(that.bounds) : that.bounds != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(name, bounds, super.hashCode());
  }

  public class BoundsNestedImpl<N> extends ClassRefFluentImpl<io.sundr.model.TypeParamDefFluent.BoundsNested<N>>
      implements io.sundr.model.TypeParamDefFluent.BoundsNested<N>, Nested<N> {
    private final ClassRefBuilder builder;
    private final int index;

    BoundsNestedImpl(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);

    }

    BoundsNestedImpl() {
      this.index = -1;
      this.builder = new ClassRefBuilder(this);

    }

    public N and() {
      return (N) TypeParamDefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endBound() {
      return and();
    }
  }

}
