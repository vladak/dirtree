
This is a piece of code that will help implement
https://github.com/oracle/opengrok/issues/3639

Specifically, efficient way of creating directories for set of absolute
file system paths is needed. Efficiency here means primarly to avoid needlessly
repeating calls to create the directories.

I.e. if the set consists of
```
/foo/bar/a.c
/foo/b.c
/foo/bar/c.c
```
then it would make sense to call just `Java.io.File.mkdirs("/foo/bar)` and all
the needed directories for this set of files are created at once.

The idea is to construct a tree structure that would contain the file system
paths and implement associated methods to extract the set of paths from the tree
that have to be passed to `mkdirs()` calls.
