package org.vladak;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    // TODO: convert this into JUnit tests
    public static void main(String[] args) throws Exception {
        TreeNode node = new TreeNode("foo");
        System.out.println(node.getPathElem());

        TreeNode bar = new TreeNode("bar");
        node.addChild(bar);
        bar.addChild(new TreeNode("xxx"));

        node.addChild(new TreeNode("haha"));

        System.out.println(TreeUtil.getLeaves(node));

        String[] paths = {
                "/beverages/coffee/espresso.java",
                "/beverages/alcohol/beer.c",
                "/beverages/alcohol/wine.scala",
                "/food/healthy/vegetarian/salad.txt",
                "/food/healthy/vegan/seeds.h",
                "/food/healthy/fruit/blueberries.hs",
                "/food/healthy/fruit/apple.objc",
                "/food/healthy/fruit/exotic/mango.il",
                "/food/unhealthy/fries.y",
                "/food/unhealthy/cake.md"
        };
        TreeNode root = new TreeNode("/");

        for (String path : paths) {
            System.out.println("Adding " + path);
            TreeUtil.addPath(path, root, true);
        }

        System.out.println("Leaves:");
        System.out.println(TreeUtil.getLeaves(root));

        System.out.println("Paths from leaves to the root:");
        for (TreeNode n : TreeUtil.getLeaves(root)) {
            System.out.println(TreeUtil.getPathToRoot(n));
        }

        Path path = Files.createTempFile("foo", "bar");
        try {
            new TreeNode(path.toString());
        } catch (TreeNodeException e) {
            System.err.println(e);
        }
    }
}
