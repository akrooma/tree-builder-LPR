
import java.util.*;

public class TreeNode {

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
   
   public static TreeNode parsePrefix (String s) {
	   validateInputString(s);
	   
	   if (!s.matches("[,()]+")) {
		   return new TreeNode(s, null, null);
	   }
	   
	   if (!s.contains(",") && s.matches("[()]+")) {
		   return childrenSequence(s);
	   }
	   
	   if (!s.contains("),") && s.matches("[,()]+")) {
		   return rootOnlyChildren(s);
	   }
       return null;  // TODO!!! return the root
   }

   public String rightParentheticRepresentation() {
      StringBuffer b = new StringBuffer();
      // TODO!!! create the result in buffer b
      return b.toString();
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
	   return null;
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
	   
	   if (input.contains("((") || input.contains("))")) {
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
		   throw new RuntimeException(input + " contains inequal amount of brackets.");
	   }
   }
   
   public static void main (String[] param) {
//      String s = "A(B1,C)";
//      TreeNode t = TreeNode.parsePrefix (s);
//      String v = t.rightParentheticRepresentation();
//      System.out.println (s + " ==> " + v); // A(B1,C) ==> (B1,C)A
	  
//	   String s = "A(B(D(G,H),E,F(I)),C(J))";
	   String s = "A(B(C(D(E))))";
	   String[] ss = s.split("\\(");
	   ss[ss.length - 1] = ss[ss.length - 1].replaceAll("[)]", "");
	   
	   
	   for (String token : ss) {
		   System.out.println("'"+token+"'");
	   }
	   
//	   String string = "A))))";
//	   
//	   System.out.println("'" + string.replaceAll("[)]", "") + "'");
	   
//	   TreeNode root = null;
//	   TreeNode temp = null;
//	   TreeNode previous = null;
//	   TreeNode current = null;
//	   
//	   for (int i = 0; i < ss.length; i++) {
//		   if (i == 0) {
//			   root = new TreeNode(ss[i], null, null);
//			   current = root;
//			   continue;
//		   }
//		   
//		   if (!ss[i].matches("[,)]")) {
//			   temp = new TreeNode(ss[i], null, null);
//			   current.setFirstChild(temp);
//			   previous = current;
//			   current = temp;
//		   }
//	   }
   }
}

