document.addEventListener('DOMContentLoaded', function() {
    // --- 既存のナビゲーションメニューの処理 ---
    const navListItems = document.querySelectorAll('nav li');
    navListItems.forEach(listItem => {
        listItem.addEventListener('click', function(event) {
            const link = this.querySelector('a');
            if (link) {
                event.preventDefault();
                window.location.href = link.href;
            }
        });
        listItem.addEventListener('focus', function() {
            this.classList.add('focused');
        });
        listItem.addEventListener('blur', function() {
            this.classList.remove('focused');
        });
    });

    // --- 既存のエラーメッセージのポップアップ処理 ---
    const errorMessageDiv = document.querySelector('.login-content .error-message');
    const messageText = errorMessageDiv ? errorMessageDiv.querySelector('small') : null;

    if (messageText && messageText.textContent.trim() !== '') {
        const popup = document.createElement('div');
        popup.classList.add('popup-message');

        const content = document.createElement('p');
        content.textContent = messageText.textContent.trim();
        popup.appendChild(content);

        // ✕ボタンやクリックイベントで閉じる機能は削除

        document.body.appendChild(popup);

        // ポップアップを表示します
        popup.style.display = 'block';

        // ★追加：3秒後にポップアップを非表示にするタイマーを設定
        setTimeout(function() {
            popup.style.display = 'none'; // 非表示にする
            popup.remove(); // DOMからポップアップを削除する
        }, 3000); // 3000ミリ秒 = 3秒
    }
});