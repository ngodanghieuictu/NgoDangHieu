<?php
	require 'connect.php';
	if (isset($_GET['phanloai'])) {
		$phanloai = $_GET['phanloai'];
		if ($phanloai == 1) {
			$thang =date('m');
			$dieukien ="(SELECT MONTH(tbl_hoadon.date_hoadon)) AND YEAR(tbl_hoadon.date_hoadon) =".date('Y');
		}elseif ($phanloai== 2) {
			$thang =date('Y');
			$dieukien ="(SELECT YEAR(tbl_hoadon.date_hoadon))";
		}
	}
	
		$query = "SELECT ten_monan,gia_monan,sl,date_hoadon
					FROM tbl_hoadon,tbl_chitiethoadon,tbl_monan
					WHERE tbl_hoadon.id = tbl_chitiethoadon.id_hoadon
					AND tbl_monan.id = tbl_chitiethoadon.id_monan
					AND ".$thang." = ".$dieukien;

	

	$data = mysqli_query($connect,$query);

	class HangXuat{
		function HangXuat($tsp,$sl,$date,$gia){
			$this->TSP    =$tsp;
			$this->SL    =$sl;
			$this->DATE   =$date;
			$this->GIA     =$gia;

		}
	}
	$mang_sp = array();
	// $mang_tongsp = array();
	// $i=0;
	while($row =mysqli_fetch_assoc($data)){
		// $mang_tongsp[$i++]=$row;
		array_push($mang_sp, new HangXuat($row['ten_monan'],$row['sl'],$row['date_hoadon'],$row['gia_monan']));
	}
	
	

	// while($row =mysqli_fetch_assoc($data)){
	// 	array_push($mang_sp, new HangXuat($row['ten_monan'],$row['sl'],$row['date_hoadon'],$row['gia_monan']));
	// }
	// $tong=0;
	// foreach ($mang_tongsp as $key) {
	// 	$tong+=$key['sl']*$key['gia_monan'];
	// }
	// echo '{"TONG":"'.$tong.'"}';
			
	echo json_encode($mang_sp);
	// if($tdn!=null){
	// 	echo "success";
	// }else{
	// 	echo "erro";
	// }

?>