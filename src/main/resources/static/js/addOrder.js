function addIngredient(ingredientName) {
    console.log("Button clicked with ingredient:", ingredientName);
    const quantityInput = document.querySelector(`input[data-name="${ingredientName}"]`);
    const quantity = quantityInput ? quantityInput.value : 1;

    fetch(`/order/add/${ingredientName}?quantity=${quantity}`, {
        method: 'POST'
    })
        .then(response => response)
        .then(data => {
            console.log("Response received:", data);
            alert(`${data.ingredientName} 추가 완료 (가격: ${data.ingredientPrice}원), 수량: ${quantity}`);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}