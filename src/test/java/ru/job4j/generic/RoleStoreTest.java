package ru.job4j.generic;

import org.junit.jupiter.api.Test;
import ru.job4j.generics.Role;
import ru.job4j.generics.RoleStore;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsAccountant() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        store.add(new Role("2", "Professor"));
        store.add(new Role("3", "Accountant"));
        Role result = store.findById("3");
        assertThat(result.getRolename()).isEqualTo("Accountant");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenReplaceThenRolenameIsProfessor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        store.replace("1", new Role("1", "Professor"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Professor");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenDeleteRoleThenRoleIsStudent() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Professor"));
        store.add(new Role("2", "Master"));
        store.add(new Role("3", "Student"));
        store.delete("1");
        Role result = store.findById("3");
        assertThat(result.getRolename()).isEqualTo("Student");
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        boolean rsl = store.replace("10", new Role("10", "Professor"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}
