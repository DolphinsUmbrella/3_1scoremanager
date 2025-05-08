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

function adjustMainContentFontSize() {
	  const nav = document.querySelector('nav');
	  const header = document.querySelector('header');
	  const footer = document.querySelector('footer');
	  const mainContent = document.querySelector('.main-content');

	  if (!nav || !header || !footer || !mainContent) {
	    return; // 要素が存在しない場合は処理を終了
	  }

	  const navWidth = nav.offsetWidth;
	  const headerHeight = header.offsetHeight;
	  const footerHeight = footer.offsetHeight;
	  const windowWidth = window.innerWidth;
	  const windowHeight = window.innerHeight;

	  // メインコンテンツが配置されるべき幅を計算 (左右のマージンやパディングも考慮)
	  // 今回のCSSでは、marginLeft でナビゲーションメニューの幅 + 20px のマージンが設定されています。
	  const availableWidth = windowWidth - (navWidth + 20);

	  // メインコンテンツの利用可能な高さを計算 (ヘッダーとフッターの高さ、上下のパディングを考慮)
	  const mainContentPaddingTop = parseFloat(window.getComputedStyle(mainContent).paddingTop);
	  const mainContentPaddingBottom = parseFloat(window.getComputedStyle(mainContent).paddingBottom);
	  const availableHeight = windowHeight - headerHeight - footerHeight - mainContentPaddingTop - mainContentPaddingBottom;

	  // 幅に基づいてフォントサイズを調整するロジック (例: 幅が広いほど大きく)
	  // ここでは、ある程度の幅を基準にフォントサイズを線形に変化させる例です。
	  const baseFontSize = 16; // 基本のフォントサイズ (px)
	  const minWidthThreshold = 600; // この幅を超えると調整を開始
	  const maxWidthThreshold = 1200; // この幅を超えると調整を上限とする
	  const fontSizeScaleFactor = 0.015; // 幅の変化に対するフォントサイズのスケール

	  let calculatedFontSize = baseFontSize;

	  if (availableWidth > minWidthThreshold) {
	    calculatedFontSize = baseFontSize + (availableWidth - minWidthThreshold) * fontSizeScaleFactor;
	    calculatedFontSize = Math.min(calculatedFontSize, baseFontSize + (maxWidthThreshold - minWidthThreshold) * fontSizeScaleFactor); // 上限を設定
	  }

	  // メインコンテンツ内のヘッダー以外の要素に適用
	  const elementsToAdjust = mainContent.querySelectorAll(':scope > *:not(.header)');
	  elementsToAdjust.forEach(element => {
	    element.style.fontSize = `${calculatedFontSize}px`;
	  });

	  // 必要に応じて、テーブルのフォントサイズなども調整
	  const table = mainContent.querySelector('table');
	  if (table) {
	    table.style.fontSize = `${calculatedFontSize * 0.9}px`;
	  }
	}

	// 初回ロード時とリサイズ時に実行
	adjustMainContentFontSize();
	window.addEventListener('resize', adjustMainContentFontSize);
