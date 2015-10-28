
import java.util.*;

public class TreeNode implements Iterator<TreeNode> {

   private String name;
   private TreeNode firstChild;
   private TreeNode nextSibling;

   TreeNode (String n, TreeNode d, TreeNode r) {
      setNodeName(n);
      setFirstChild(d);
      setNextSibling(r);
   }
   
   public void setNodeName(String n) {
	   this.name = n;
   }
   
   public void setFirstChild(TreeNode d) {
	   this.firstChild = d;
   }
   
   public void setNextSibling(TreeNode r) {
	   this.nextSibling = r;
   }
   
   public boolean hasChild() {
	   return this.firstChild != null;
   }
   
   public String getName() {
	   return this.name;
   }
   
   public TreeNode getChild() {
	   return this.firstChild;
   }
   
   public TreeNode getSibling() {
	   return this.nextSibling;
   }
   
   public TreeNode next() {
	      return getSibling();
   }
   
   public boolean hasNext() {
	      return (getSibling() != null);
   }
   
   public Iterator<TreeNode> children() {
	      return getChild();
   }
   
   public static TreeNode parsePrefix (String s) {
	   validateInputString(s);
	   
	   if (!s.contains("(") && !s.contains(",") && !s.contains(")")) {
		   return new TreeNode(s, null, null);
	   } else if (!s.contains(",") && s.contains("(") && s.contains(")")) {
		   return childrenSequence(s);
	   } else if (!s.contains("),")) {
		   return rootOnlyChildren(s);
	   }
       return null;  // TODO!!! return the root
   }

   public String rightParentheticRepresentation() {
      StringBuffer b = new StringBuffer();
      
	  Iterator<TreeNode> child = children();
	  
	  if (child != null) {
		  b.append("(");
	  }
	  
	  while (child != null) {
		  b.append(((TreeNode) child).rightParentheticRepresentation());
		  
		  if (child.hasNext()) {
			  b.append(",");
		  } else {
			  b.append(")");
		  }
		  
		  child = (TreeNode)child.next();
	  }
	  
      b.append(name);
      return b.toString();
   }
   
   public String lpr() {
	   String result = name;
	   result += firstChild == null ? "" : "(" + firstChild.lpr() + ")";
	   result += nextSibling == null ? "" : "," + nextSibling.lpr();
	   return result;
   }
   
   public static TreeNode childrenSequence(String input) {
	   TreeNode root = null;
	   TreeNode current = null;
	   TreeNode temp = null;
	   
	   String[] array = input.split("\\(");
	   array[array.length - 1] = array[array.length - 1].replaceAll("[)]", "");

	   for (int i = 0; i < array.length ; i++) {
		   if (i == 0) {
			   root = new TreeNode(array[i], null, null);
			   current = root;
		   } else {
			   temp = new TreeNode(array[i], null, null);
			   current.setFirstChild(temp);
			   current = temp;
		   }
	   }
	   return root;
   }
   
   public static TreeNode rootOnlyChildren (String input) {
	   TreeNode root = null;
	   TreeNode current = null;
	   TreeNode temp = null;
	   
	   input = input.replace(")", "");
	   
	   String[] tree = input.split("\\(");
	   String[] children = tree[1].split("\\,");
	   
	   temp = new TreeNode(children[0], null, null);
	   root = new TreeNode(tree[0], temp, null);
	   current = temp;
	   
	   for (int i = 1; i < children.length; i++) {
		   temp = new TreeNode(children[i], null, null);
		   current.setNextSibling(temp);
		   current = temp;
	   }
	   
	   return root;
   }
   
   public static void validateInputString(String input) {
	   if (input.contains("()")) {
		   throw new RuntimeException(input + " contains empty subtree.");
	   }
	   
	   if (input.contains(",,")) {
		   throw new RuntimeException(input + " contains double commas.");
	   }
	   
	   if (input.contains(" ")) {
		   throw new RuntimeException(input + " contains empty spaces.");
	   }
	   
	   if (input.contains("((")) {
		   throw new RuntimeException(input + " contains double brackets.");
	   }
	   
	   if (input.contains(",") && (!input.contains("(") || !input.contains(")"))) {
		   throw new RuntimeException(input + " contains no brackets.");
	   }
	   
	   int leftParanthesis = 0;
	   int rightParanthesis = 0;
	   
	   for (int i = 0;  i < input.length() ; i++) {
		   if (input.charAt(i) == '(') {
			   leftParanthesis++;
		   } else if (input.charAt(i) == ')') {
			   rightParanthesis++;
		   }
	   }
	   
	   if (leftParanthesis != rightParanthesis) {
		   throw new RuntimeException(input + " is unbalanced.");
	   }
   }
   
   public static void main (String[] param) {
//      String s = "A(B1,C)";
//      TreeNode t = TreeNode.parsePrefix (s);
//      String v = t.rightParentheticRepresentation();
//      System.out.println (s + " ==> " + v); // A(B1,C) ==> (B1,C)A
	  
//	   String s = "A(B(D(G,H),E,F(I)),C(J))";
//	   String s = "A(B,C,D,E,D,F,G)";
	   String s = "A(B(C(D)))";
//	   String s = "A(B1,C)";
	   
//	   s = s.replace(")", "");
//	   
//	   String[] tree = s.split("\\(");
//	   String[] children = tree[1].split("\\,");
//	   
//	   for (String token : tree) {
//		   System.out.println(token);
//	   }
//	   
//	   System.out.println();
//	   
//	   for (String token : children) {
//		   System.out.println(token);
//	   }
	   
//	   TreeNode node = new TreeNode("A", new TreeNode("B", null, new TreeNode("C", new TreeNode("D", null, null),
//			   new TreeNode("X", new TreeNode("Z", null, new TreeNode("O", null, null)), null))
//			   ), null);
	   
	   TreeNode node = TreeNode.parsePrefix(s);
	   String sRpr = node.rightParentheticRepresentation();
	   String sLpr = node.lpr();
	   
	   System.out.println(sRpr);
	   System.out.println(sLpr);
	   
//	   String[] ss = s.split("\\(");
//	   
//	   for (String token : ss) {
//		   System.out.println("'"+token+"'");
//	   }

   }
}

