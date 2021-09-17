package org.vladak;

import java.io.File;

public class Main {

    // TODO: convert this into JUnit tests
    public static void main(String[] args) throws Exception {
        TreeNode node = new TreeNode("foo");
        TreeNode bar = new TreeNode("bar");
        node.addChild(bar);
        bar.addChild(new TreeNode("xxx"));
        node.addChild(new TreeNode("haha"));
        System.out.println(TreeUtil.getLeaves(node));

        // TODO: read a list of paths, add them to a tree, print the paths from the leaves to the root
    }
}
