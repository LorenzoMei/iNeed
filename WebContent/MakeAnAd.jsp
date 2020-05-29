<%@ include file="components/TitleSetter.jsp"%>
<%@ include file="components/AuthChecker.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="logic.publishanad.PublishAnAdController"%>

   
    
    
    
<jsp:useBean id="publishAnAdBean" scope="request" 
	class="logic.beans.PublishAnAdBean"></jsp:useBean>

<jsp:setProperty property="*" name="publishAnAdBean"/>

<%
	PublishAnAdController controller = PublishAnAdController.getInstance();

	if(request.getParameter("publish") != null){
		String other = request.getParameter("other");
		String username = session.getAttribute("username").toString();
		String typeRequest = "Request";
		
		if(other != null){
			%>
			<jsp:setProperty name="publishAnAdBean" property="category" value="<%= other%>"/>
			<%
		}
		
		if(request.getParameter("type").compareTo(typeRequest) == 0)
			publishAnAdBean.setRequestType();
		else
			publishAnAdBean.setOfferType();
		%>
		<jsp:setProperty name="publishAnAdBean" property="ownerUsername" value="<%= username %>"/>
		<%
		controller.createAd(publishAnAdBean);
		%>
		<jsp:forward page="ViewFlow.jsp"></jsp:forward>
		<%
	}
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/style_maa.css">
	<title>Make an ad</title>
	
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
</head>
<body id="corp">
	
	
	<div class="row">
	<div class="container-fluid">
		<jsp:include page="components/ToolBar.jsp"></jsp:include>
	</div>
		<br>
	
		<div class="col-lg-4"></div>
		 <div class="container"> 
            <p id="logos">Make An Ad</p>

            <div class="jumbotron">
                <br>
                <div class="card" id="card-style">
                    
                    <img class="card-img-center" src="image/ad.png" text="a" alt="Adlogo">
                    <div class="card-body">
                        <form action="MakeAnAd.jsp" name="myform" method="POST">
                            
                            <div class="form-group">
                                <label for="type">Type:</label>
                                <select class="form-control" id="type" name="type">
                                    <option>Request</option>
                                    <option>Offer</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="category">Category:</label>
                                <select class="form-control" id="category" name="category" onchange="enabledOther()">
                                    <option value="Electronics">Electronics</option>
                                    <option value="Hydraulics">Hydraulics</option>
                                    <option value="Gardering">Gardering</option>
                                    <option value="Informatic">Informatic</option>
                                    <option value="Bed sharing">Bed sharing</option>
                                    <option value="Other">Other...</option>
                                </select>
                            </div>

                            <div class="field">
                           
                                <section class="content bgcolor-1">
									<span class="input input--nao">
										<input class="input__field input__field--nao" type="text" id="other" disabled/>
										<label class="input__label input__label--nao" for="input-3">
											<span class="input__label-content input__label-content--nao">Other</span>
										</label>
										<svg class="graphic graphic--nao" width="300%" height="100%" viewBox="0 0 1200 60" preserveAspectRatio="none">
											<path d="M0,56.5c0,0,298.666,0,399.333,0C448.336,56.5,513.994,46,597,46c77.327,0,135,10.5,200.999,10.5c95.996,0,402.001,0,402.001,0"/>
										</svg>
									</span>
									
									<span class="input input--nao">
										<input class="input__field input__field--nao" type="text" id="input-1" required/>
										<label class="input__label input__label--nao" for="title">
											<span class="input__label-content input__label-content--nao">Title</span>
										</label>
										<svg class="graphic graphic--nao" width="300%" height="100%" viewBox="0 0 1200 60" preserveAspectRatio="none">
											<path d="M0,56.5c0,0,298.666,0,399.333,0C448.336,56.5,513.994,46,597,46c77.327,0,135,10.5,200.999,10.5c95.996,0,402.001,0,402.001,0"/>
										</svg>
									</span>
								</section>
                            </div>
							<br>
                            <div class="field">
                                <label for="body">Body:</label><br>
                                <textarea id="body" name="body" style="resize:none; width:100%" required></textarea>
                            </div>

							<br>

                            <div>
                                <button style="width: 100%; height: 20%;" name="publish" type="submit" id="publish"  value="Publish" class="btn btn-sep icon-send" ><i class="fas fa-paper-plane"></i>   Publish</button>
                            </div>
                        </form>
                  </div>
                </div>
                <br>
            </div>
        </div>
     <script src="js/classie.js"></script>
    <script>
    	function enabledOther(){
    		var value = document.getElementById("category").value;
    		if(value == "Other"){
    			$("#other").prop("disabled", false);
    		}
    		else{
    			$("#other").prop("disabled", true);
    		}
    	}
    	
    	(function() {
			if (!String.prototype.trim) {
				(function() {
					var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
					String.prototype.trim = function() {
						return this.replace(rtrim, '');
					};
				})();
			}

			[].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
				// in case the input is already filled..
				if( inputEl.value.trim() !== '' ) {
					classie.add( inputEl.parentNode, 'input--filled' );
				}

				// events:
				inputEl.addEventListener( 'focus', onInputFocus );
				inputEl.addEventListener( 'blur', onInputBlur );
			} );

			function onInputFocus( ev ) {
				classie.add( ev.target.parentNode, 'input--filled' );
			}

			function onInputBlur( ev ) {
				if( ev.target.value.trim() === '' ) {
					classie.remove( ev.target.parentNode, 'input--filled' );
				}
			}
		})();
    	
    </script>
</body>
</html>