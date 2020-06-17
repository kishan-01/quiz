import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class QuizEngine
{

int count=1,a,score=0;
String ids="",mychoice,ans;
String id;
Frame f;
Label l;
JRadioButton rb1,rb2,rb3,rb4;
Button b;

void randomid()
{
while(true)
{
a=((int)(Math.random()*8))+1;
id=""+a;
if(ids.contains(id)==false)
{
ids=ids+","+id;
break;
}
}
}
void randomquestion()
{
randomid();
try
{
String query="select * from questions where id=?";
Class.forName("com.mysql.jdbc.Driver");
Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?user=root&password=12345");
PreparedStatement st=cn.prepareStatement(query);
st.setInt(1,a);
ResultSet rs=st.executeQuery();
rs.next();
String question=rs.getString(2);
String op1=rs.getString(3);
String op2=rs.getString(4);
String op3=rs.getString(5);
String op4=rs.getString(6);
ans=rs.getString(7);
l.setText(question);
rb1.setText(op1);
rb2.setText(op2);
rb3.setText(op3);
rb4.setText(op4);
}
catch(Exception e)
{
System.out.println(e.getMessage());
}
}

QuizEngine()
{
f=new Frame();
l=new Label();
rb1=new JRadioButton("");
rb2=new JRadioButton("");
rb3=new JRadioButton("");
rb4=new JRadioButton("");
b=new Button("Next");
b.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
if(count==5)
{
if(rb1.isSelected())
mychoice="A";
if(rb2.isSelected())
mychoice="B";
if(rb3.isSelected())
mychoice="C";
if(rb4.isSelected())
mychoice="D";
if(ans.equals(mychoice))
score++;
JOptionPane.showMessageDialog(null,"Your Score is "+score);
f.dispose();
}
else
{
if(rb1.isSelected())
mychoice="A";
if(rb2.isSelected())
mychoice="B";
if(rb3.isSelected())
mychoice="c";
if(rb4.isSelected())
mychoice="D";
if(ans.equals(mychoice))
score++;
//JOptionPane.showMessageDialog(null,"Your Score is "+score+" "+mychoice+" "+ans);
randomquestion();
count=count+1;
}
}
});
ButtonGroup gp=new ButtonGroup();
gp.add(rb1);
gp.add(rb2);
gp.add(rb3);
gp.add(rb4);
f.setLayout(new GridLayout(6,1));
f.add(l);
f.add(rb1);
f.add(rb2);
f.add(rb3);
f.add(rb4);
f.add(b);
f.setVisible(true);
f.setSize(400,400);
randomquestion();

}
}