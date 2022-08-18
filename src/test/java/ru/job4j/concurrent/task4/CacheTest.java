package ru.job4j.concurrent.task4;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(1, 1);
        base1.setName("Stas");
        base2.setName("Arcady");
        cache.add(base1);
        cache.add(base2);
        Assert.assertThat(cache.getMemoryForTest().get(1).getName(), is("Stas"));
    }

    @Test
    public void addCheckByReturnBoolean() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);
        Base base3 = new Base(1, 1);
        boolean rsl1 = cache.add(base1);
        boolean rsl2 = cache.add(base2);
        boolean rsl3 = cache.add(base3);
        Assert.assertTrue(rsl1);
        Assert.assertTrue(rsl2);
        Assert.assertFalse(rsl3);
    }

    @Test
    public void whenUpdateEqualVersions() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(1, 1);
        base1.setName("Stas");
        base2.setName("Arcady");
        cache.add(base1);
        cache.update(base2);
        Assert.assertThat(cache.getMemoryForTest().get(1).getName(), is("Arcady"));
        Assert.assertThat(cache.getMemoryForTest().get(1).getVersion(), is(2));
    }

    @Test
    public void whenUpdateNotEqualVersions() {
        RuntimeException thrown = Assertions.assertThrows(OptimisticException.class, () -> {
            Cache cache = new Cache();
            Base base1 = new Base(1, 1);
            Base base2 = new Base(1, 1);
            Base base3 = new Base(1, 1);
            base1.setName("Stas");
            base2.setName("Arcady");
            cache.add(base1);
            cache.update(base2);
            cache.update(base3);
        }, "Versions are not equal");
        Assertions.assertEquals("Versions are not equal", thrown.getMessage());
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        cache.add(base2);
        cache.delete(base2);
        Assert.assertNull(cache.getMemoryForTest().get(2));
    }
}