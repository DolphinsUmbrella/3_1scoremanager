document.addEventListener('DOMContentLoaded', function() {
  const navListItems = document.querySelectorAll('nav li');

  navListItems.forEach(listItem => {
    listItem.addEventListener('click', function(event) {
      const link = this.querySelector('a');
      if (link) {
        // aタグのデフォルトのクリックイベントを防止
        event.preventDefault();
        // aタグのhref属性に遷移
        window.location.href = link.href;
      }
    });

    // タブ操作でli要素にフォーカスが当たった際の処理（任意）
    listItem.addEventListener('focus', function() {
      this.classList.add('focused'); // フォーカス時のスタイルを適用（CSSで定義）
    });

    listItem.addEventListener('blur', function() {
      this.classList.remove('focused'); // フォーカスが外れたらスタイルを削除
    });
  });
});

document.addEventListener('DOMContentLoaded', function() {
	  const header = document.querySelector('.main-content .header');
	  const mainContent = document.querySelector('.main-content > *:not(.header)'); // ヘッダー以外のメインコンテンツ直下の子要素

	  if (header && mainContent) {
	    const headerHeight = header.offsetHeight;

	    // 常にヘッダーの高さ分のパディングを維持
	    mainContent.style.paddingTop = headerHeight + 'px';
	  }
	});