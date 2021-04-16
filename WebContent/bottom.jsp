<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./css/bottom.css">
 

</head>

<body>
    <div class="footer-dark">
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-6" align="center">
                        <h3>Services</h3>
                        <ul>
                            <li><a href="rescued?param=list">AdoptableDogs</a></li>
                            <li><a href="abandog?param=reportADPage">Report Animal Cruelty</a></li>
                            
                        </ul>
                    </div>
                    <div class="col-md-6" align="center">
                        <h3>About</h3>
                        <ul>
                            <li><a id="whoweare" data-toggle="modal" style="cursor:pointer">Who We Are</a></li>
                            <li><a id="partners" data-toggle="modal" style="cursor:pointer">Partner Organizations</a></li>
                            <li><a href="mem?param=Info">Contact</a></li>
                        </ul>
                    </div>
                    </div>
                    <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
            <div class="col item social"><a href="https://www.facebook.com/savekoreandogs"><i class="icon ion-social-facebook"></i></a><a href="https://twitter.com/NamiKim_DogsSK"><i class="icon ion-social-twitter"></i></a><a href="https://www.instagram.com/savekoreandogs_skd/"><i class="icon ion-social-instagram"></i></a></div>
               
                <p class="copyright">Change A Pet's Life <i class="fa fa-copyright"></i>2021 | All Rights Reserved</p></div>
                <div class="col-md-4"></div>
                </div>
            </div>
        </footer>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
    
  <div class="modal" id="exampleModal">  
    <div class="modal-dialog modal-dialog-centered">
  		<div class="modal-content">
     		<div class="modal-header">
		        <h5 class="modal-title">Partner Organizations</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
      </div>
      <div class="modal-body">
        <h5><a href="https://ladyfreethinker.org/"><img alt="" src="./images/ladyfreethinker.jpg" style="max-height: 80px;"></a>	Lady Freethinker</h5>
        <h5><a href="https://willows-wish.org/"><img alt="" src="./images/willows-wish.jpg" style="max-height: 80px;"></a>		Willow's Wish</h5>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
	</div>
	</div>
    
	<div class="modal" id="exampleModal2">  
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
  		<div class="modal-content">
     		<div class="modal-header">
		        <h5 class="modal-title">Who We Are</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
      </div>
      <div class="modal-body">
        <p>Change A Pet's Life is a global foundation focused on improving animal welfare not only locally in the Korea but internationally in countries throughout Asia as well. 
        We believe that in order to impact change, you must offer educational and practical solutions. 
        We know that we cannot rescue the millions of animals in need but we can help change the landscape for future generations through targeted programs and initiatives focused on achieving sustainable change.
		Our goals are to focus on educational efforts, sustainable long-term solutions, focused research and database development, targeted sterilization and vaccination programs, 
		and cultural awareness/understanding. 
		While we know we cannot save all of the animals, we do recognize the need for an element of rescue within our organization and are driven by our compassion to work diligently and 
		collaboratively with international partner rescues to help animals subjected to abuse (specifically the dog and cat meat trade), to not only get the proper medical attention but 
		to also help in finding placement into forever loving homes.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
	</div>
	</div>
	
	
	
	
	
	
    <script type="text/javascript">
    $("#partners").click(function() {
        $("#exampleModal").removeClass("modal fade");
        $("#exampleModal").addClass("modal");
        $("#exampleModal").addClass("modal fade");
       
        $("#exampleModal").modal();
       
    });
    $("#whoweare").click(function() {
        $("#exampleModal2").removeClass("modal fade");
        $("#exampleModal2").addClass("modal");
        $("#exampleModal2").addClass("modal fade");
       
        $("#exampleModal2").modal();
       
    });
    </script>
    
</body>

</html>
