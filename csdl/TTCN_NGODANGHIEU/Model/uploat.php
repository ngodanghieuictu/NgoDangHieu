<?php

	$file_path ="/Applications/XAMPP/xamppfiles/htdocs/TTCN_NGODANGHIEU/img/hang/";
	$file_path = $file_path.basename($_FILES['uploaded_file']['name']);

	if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)){
		echo $_FILES['uploaded_file']['name'];
	}else{
		echo "Failed";
	}
	// if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'],'/Applications/XAMPP/xamppfiles/htdocs/TTCN_NGODANGHIEU/img/'.$_FILES['uploaded_file']['name'])){
 //        echo $_FILES['uploaded_file']['name'];
 //    }else{
 //        echo "Failed";
 //    }
?>