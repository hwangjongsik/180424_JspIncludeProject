function postfind()
{
	window.open("PostServlet","ps","width=600,height=350,scrollbars=yes");
}
/*
 *   Window => Document => Form => Input.textarea,select
 *   
 *    000-111
 */
function ok(zip,addr)
{
	//alert("aa");
	opener.frm.post1.value=zip.substring(0,3);
	opener.frm.post2.value=zip.substring(4,7);
	opener.frm.addr1.value=addr;
	self.close();
}
