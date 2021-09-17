package org.vladak;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TreeNodeTest {
    @Test
    void testInvalidTreeNodeParameter() throws IOException {
        Path path = Files.createTempFile("foo", "bar");
        assertThrows(TreeNodeException.class, () -> {
            new TreeNode(path.toString());
        });
    }
}
