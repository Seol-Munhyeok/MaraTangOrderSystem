function addIngredient(ingredientName) {
    fetch(`/order/add/${ingredientName}`, {
        method: 'POST'
    })
        .then(response => response.json())
        .then(data => {
            alert(`${data.name} 추가 완료 (가격: ${data.price}원)`);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}