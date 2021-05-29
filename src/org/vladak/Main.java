package org.vladak;

public class Main {

    public static void main(String[] args) throws Exception {
        TreeNode node = new TreeNode("foo");
        System.out.println(node.getPathElem());

        TreeNode bar = new TreeNode("bar");
        node.addChild(bar);
        bar.addChild(new TreeNode("xxx"));

        node.addChild(new TreeNode("haha"));

        System.out.println(TreeUtil.getLeaves(node));

        String[] paths = {"/beverages/coffee/espresso.java",
                "/beverages/alcohol/beer.c",
                "/food/healthy/vegetarian/salad.txt",
                "/food/healthy/fruit/blueberries.hs",
                "/food/unhealthy/cake.md"};
        TreeNode root = new TreeNode("/");
        for (String path : paths) {
            TreeUtil.addPath(path, root);
        }
        System.out.println(TreeUtil.getLeaves(root));
        for (TreeNode n : TreeUtil.getLeaves(root)) {
            System.out.println(TreeUtil.getPathToRoot(n));
        }
    }
}
