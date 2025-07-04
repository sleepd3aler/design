package ru.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsArtem() {
        RoleStore roleStore = new RoleStore();
        roleStore.add((new Role("1", "Artem")));
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Artem");
    }

    @Test
    void whenReplaceThenUsernameIsAndrey() {
        RoleStore roleStore = new RoleStore();
        roleStore.add((new Role("1", "Artem")));
        roleStore.replace("1", (new Role("1", "Andrey")));
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Andrey");
    }

    @Test
    void whenReplaceWithNoneExistentIdThenUsernameIsSame() {
        RoleStore roleStore = new RoleStore();
        roleStore.add((new Role("1", "Artem")));
        roleStore.replace("666", (new Role("666", "Andrey")));
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Artem");
    }

    @Test
    void whenDeleteThenUsernameIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add((new Role("1", "Artem")));
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenDeleteWithNoneExistentIdThenUsernameIsArtem() {
        RoleStore roleStore = new RoleStore();
        roleStore.add((new Role("1", "Artem")));
        roleStore.delete("666");
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Artem");
    }

    @Test
    void whenReplaceSuccessfulThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add((new Role("1", "Artem")));
        boolean result = roleStore.replace("1", (new Role("1", "Andrey")));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceWithNoneExistentIdThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add((new Role("1", "Artem")));
        boolean result = roleStore.replace("666", (new Role("666", "Andrey")));
        assertThat(result).isFalse();
    }
}