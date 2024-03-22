// 검색 버튼 클릭 시 호출되는 함수
document.getElementById("searchButton").addEventListener("click", function() {
    // 검색어 입력 필드에서 검색어를 가져옴
    const searchKeyword = document.getElementById("search").value.toLowerCase();
    // 모든 결과 아이템을 가져옴
    const results = document.querySelectorAll(".resultItem");

    // 각 결과 아이템을 순회하면서 검색어와 일치하는지 확인하여 화면에 표시 여부를 결정
    results.forEach(function(result) {
        const content = result.querySelector(".content").textContent.toLowerCase(); // 내용 텍스트를 가져와 소문자로 변환
        const writer = result.querySelector(".writer").textContent.toLowerCase(); // 작성자 텍스트를 가져와 소문자로 변환

        // 검색어가 내용이나 작성자에 포함되어 있으면 화면에 표시, 아니면 숨김
        if (content.includes(searchKeyword) || writer.includes(searchKeyword)) {
            result.style.display = "block"; // 화면에 표시
        } else {
            result.style.display = "none"; // 숨김
        }
    });
});