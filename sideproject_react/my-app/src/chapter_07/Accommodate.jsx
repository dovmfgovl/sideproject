import React, {useState, useEffect} from "react";
import useCounter from "./useCounter";

const MAX_CAPACITY = 10;

function Accommodate(props) {
  const [isFull, setIsFull] = useState(false);
  const [count, increaseCount, decreaseCount] = useCounter(0);

  useEffect(() => {
    console.log("=================");
    console.log("useEffect() is called.");
    console.log(`isFull: ${isFull}`);
  });

  useEffect(() => {
    setIsFull(count >= MAX_CAPACITY);
    console.log(`Current count value: ${count}`);
  }, [count]);

  return (
    <div style={{ padding: 16 }}>
      <p>{`총 ${count}명 수용했습니다.`}</p>

      <button onClick={increaseCount} disabled={isFull}>
        입장
      </button>
      <button onClick={decreaseCount}>퇴장</button>
    </div>
  );
}

export default Accommodate;
// useCounter Hook을 사용해 카운트를 관리함.
// 최대 카운트 개수는 MAX_CAPACITY라는 이름의 상수로 정의.
// 카운트 개수가 최대 용량을 초과하면 경고 문구가 표시되며 더 이상 입장 불가.
// useEffect Hook 작동 방식 확인 위해 일부러 두 개의 useEffect Hook 사용
//  1) 의존성 배열 없는 형태 : 컴포넌트 마운트 된 직후 호출. 이후 컴포넌트 업데이트 될 때마다 호출.
//  2) 의존성 배열 있는 형태 : 컴포넌트가 마운트 된 직후 호출. 이후 카운트 값 바뀔 때마다 호출. 
//                            이때 용량 가득 찼는지 아닌지의 상태를 isFull state에 저장.