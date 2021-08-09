<%@page import="Controller.CommentController"%>
<%@page import="Controller.TweetController"%>
<%@include file="config.jsp" %>
<%
TweetController twm = new TweetController();
if(request.getMethod().equals("POST"))
{
    if(request.getParameter("ONTWEET")!= null)
    {
        user.setUsername(request.getParameter("COMMENTBY"));
        user.setId(request.getParameter("ONTWEET"));
        user.setMessage(request.getParameter("ACTUALCOMMENT"));
        if(CommentController.comment(user))
        {
        out.print("success");
        }
        
    }
    else{
  user.setUsername(request.getParameter("TWEETEDBY"));
  user.setTweet(request.getParameter("TWEET"));
  
  twm.tweet(user);
  out.print(user.getMessage());
    }

}

else
{
    user.setUsername(request.getParameter("IMG"));
    user.setId(request.getParameter("TWEETOF"));
    out.print(twm.getTweet(user));
   
    }
  

%>
