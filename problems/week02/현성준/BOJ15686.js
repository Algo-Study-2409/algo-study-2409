// https://www.acmicpc.net/problem/15686

/*
* 현성준 : BOJ 15686 치킨 배달
* 324ms
*
* */

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
    const [N, M] = input[0].split(" ").map(Number); // N: 도시 크기, M: 남길 치킨집 수
    const city = []; // 도시 정보를 저장할 배열
    const houses = []; // 집의 위치를 저장할 배열
    const chickenHouses = []; // 치킨집의 위치를 저장할 배열

    // 도시 정보 입력 처리
    for (let i = 1; i <= N; i++) {
        const row = input[i].split(" ").map(Number);
        city.push(row);

        // 집과 치킨집의 위치를 저장
        for (let j = 0; j < N; j++) {
            if (row[j] === 1) houses.push([i - 1, j]); // 집의 위치
            if (row[j] === 2) chickenHouses.push([i - 1, j]); // 치킨집의 위치
        }
    }

    // 1. 조합을 구하기 위해 필요한 함수 (치킨집 중 M개를 선택)
    const getCombinations = (arr, selectNumber) => {
        if (selectNumber === 1) return arr.map((v) => [v]);
        const results = [];
        arr.forEach((fixed, index, origin) => {
            const rest = origin.slice(index + 1); // 고정된 것 다음 요소부터 선택
            const combinations = getCombinations(rest, selectNumber - 1); // 재귀 호출
            const attached = combinations.map((combo) => [fixed, ...combo]);
            results.push(...attached);
        });
        return results;
    };

    // 2. 도시의 치킨 거리 계산 함수
    const getCityChickenDistance = (selectedChickens) => {
        let totalDistance = 0;

        // 모든 집에 대해, 선택된 치킨집들 중 가장 가까운 치킨집과의 거리를 구함
        houses.forEach(([hx, hy]) => {
            let minDistance = Infinity;

            selectedChickens.forEach(([cx, cy]) => {
                const distance = Math.abs(hx - cx) + Math.abs(hy - cy);
                minDistance = Math.min(minDistance, distance); // 최소 거리 갱신
            });

            totalDistance += minDistance;
        });

        return totalDistance;
    };

    // 3. M개의 치킨집 선택한 경우의 수 구하기 (조합)
    const chickenCombinations = getCombinations(chickenHouses, M);

    // 4. 각 조합에 대해 도시의 치킨 거리 구해서 최소값 찾기
    let minimumCityChickenDistance = Infinity;

    chickenCombinations.forEach((selectedChickens) => {
        const cityChickenDistance = getCityChickenDistance(selectedChickens);
        minimumCityChickenDistance = Math.min(minimumCityChickenDistance, cityChickenDistance);
    });

    console.log(minimumCityChickenDistance); // 최소 치킨 거리 출력
}

solution(input);