function go(url)
{
	window.location = url;
}

function newContact()
{
	window.location = "saveMandir.html";
}

function deleteContact(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
}
