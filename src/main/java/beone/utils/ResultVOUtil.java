package beone.utils;

import beone.vo.ResultVO;

public class ResultVOUtil {
	
	public static <T> ResultVO<T> success(String msg, T data) {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(200);
        resultVO.setMsg(msg);
        resultVO.setData(data);
        return resultVO;
    }
	
	public static<T> ResultVO<T> fail(String msg, T data) {
		ResultVO<T> resultVO = new ResultVO<T>();
		resultVO.setCode(400);
		resultVO.setMsg(msg);
		resultVO.setData(data);
		return resultVO;
	}
	
	
	
	
}
