package org.vladak;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeUtilTest {
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
