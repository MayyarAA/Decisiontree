package project.p3;

public class QuestionNode {
	
	public String data;
	public QuestionNode left;
	public QuestionNode right;
	
	public QuestionNode(String info) {
		this(info,null,null);
	}
	public QuestionNode(String info,QuestionNode left, QuestionNode right) {
		this.data=info;
		this.left=left;
		this.right=right;
	}
	

}
