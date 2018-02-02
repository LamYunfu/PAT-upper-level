# Kuchiguse 

### **题目描述**

```
The Japanese language is notorious for its sentence ending particles. Personal preference of such particles can be considered as a reflection of the speaker's personality. Such a preference is called "Kuchiguse" and is often exaggerated artistically in Anime and Manga. For example, the artificial sentence ending particle "nyan~" is often used as a stereotype for characters with a cat-like personality:
Itai nyan~ (It hurts, nyan~)
Ninjin wa iyada nyan~ (I hate carrots, nyan~)

Now given a few lines spoken by the same character, can you find her Kuchiguse?
notorious 声名狼藉的
particles 微粒
artistically	在艺术上
Anima		日本动漫
stereotype 		陈腔滥调
```

### **输入描述:**

```
Each input file contains one test case.  For each case, the first line is an integer N (2<=N<=100). Following are N file lines of 0~256 (inclusive) characters in length, each representing a character's spoken line. The spoken lines are case sensitive.
```

### **输出描述:**

```
For each test case, print in one line the kuchiguse of the character, i.e., the longest common suffix of all N lines. If there is no such suffix, write "nai".
suffix   后缀
```

### **输入例子:**

```
3
Itai nyan~
Ninjin wa iyadanyan~
uhhh nyan~
```

### **输出例子:**

```
nyan~
```
