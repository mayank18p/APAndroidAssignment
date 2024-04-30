// Android Assignment to efficiently load and display images in a scrollable grid:

I used Retrofit third-party library to call the given API to consume its data.
Implemented custom asynchronous image loading process, did not use any third-party image loading libray to show the images.
Showing 3-column image grid. Images are center-cropped.
Implemented custom pagination to show all 100 images (10 images per scroll)
Developed a caching mechanism (Through LruCache) to store images retrieved from the API in both memory and disk cache for efficient retrieval.
Handled network error if api fails or when internet is not available.
Implementation is done in native way using Java language. 
