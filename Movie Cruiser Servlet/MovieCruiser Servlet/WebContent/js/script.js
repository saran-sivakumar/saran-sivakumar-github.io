function validateMoviesForm(){
    var title=document.forms["editMovieList"]["title"].value;
    if(title==""){
        alert('Title is required');
        return false;
    }
    var titleLength=title.length;
    if(titleLength < 2 || titleLength > 100){
        alert('Title should have 2 to 100 charecters');
        return false;
    }
 
    
    var price=document.forms["editMovieList"]["boxOffice"].value;
    if(isNaN(price)){
        alert('Box office has to be anumber'); 
        return false;
    }
    if(price==""){
        alert('Box office is required');
        return false;
    }
    var dateOfLaunch=document.forms["editMovieList"]["dateOfLaunch"].value;
    if(dateOfLaunch=="")
    {
        alert('Date of Launch is required');
        return false;
    }
    if(!dateOfLaunch.match(/^(0[1-9]|[12][0-9]|3[01])[\- \/.](?:(0[1-9]|1[012])[\-\/.](19|20)[0-9]{2})$/)){
        alert('incorrect date format expected format (dd/mm/yyyy)');
        return false;
    }
    var genre=document.forms["editMovieList"]["genre"].value;
    if(genre=="0"){
        alert('Select one Genre');
        return false;
    }
    
}
