function addIngredient(ingredientName) {
    const quantityInput = document.querySelector(`input[data-name="${ingredientName}"]`);
    const quantity = quantityInput ? quantityInput.value : 1;

    fetch(`/order/add/${ingredientName}?quantity=${quantity}`, {
        method: 'POST'
    })
        .then(response => response.json())
        .then(data => {
            alert(`${data.name} 추가 완료 (가격: ${data.price}원), 수량: ${quantity}`);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}