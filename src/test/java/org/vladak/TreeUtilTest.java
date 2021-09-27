package org.vladak;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TreeUtilTest {
    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testDifferentRoots() throws TreeException {
        assertTrue(File.listRoots().length > 1);

        File root1 = File.listRoots()[0];
        File root2 = File.listRoots()[1];
        assertNotEquals(root1, root2);

        File file = new File(root2, "foo");
        assertThrows(TreeException.class,
                () -> TreeUtil.addPath(file.toString(), new TreeNode(root1.toString()), false));
    }

    @Test
    void testAddPath() throws TreeException {
        String[] paths = {
                "beverages/coffee/espresso.java",
                "beverages/alcohol/beer.c",
                "beverages/alcohol/wine.scala",
                "food/healthy/vegetarian/salad.txt",
                "food/healthy/vegan/seeds.h",
                "food/healthy/fruit/blueberries.hs",
                "food/healthy/fruit/apple.objc",
                "food/healthy/fruit/exotic/mango.il",
                "food/unhealthy/fries.y",
                "food/unhealthy/cake.md"
        };
        String rootString = File.listRoots()[0].toString();
        TreeNode root = new TreeNode(rootString);

        for (String path : paths) {
            path = path.replace("/", File.separator);
            path = rootString + path;
            System.out.println("Adding " + path);
            TreeUtil.addPath(path, root, true);
        }

        System.out.println("Leaves:");
        System.out.println(TreeUtil.getLeaves(root));
        assertEquals(6, TreeUtil.getLeaves(root).size());

        System.out.println("Paths from leaves to the root:");
        for (TreeNode n : TreeUtil.getLeaves(root)) {
            System.out.println(TreeUtil.getPathToRoot(n));
        }
        // TODO: check the paths
    }
}
